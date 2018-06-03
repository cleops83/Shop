import 'react-sortable-tree/style.css';
import React from 'react';
import SortableTree, {getTreeFromFlatData} from 'react-sortable-tree';

const ACCESS_KEY = '4007203118c696a1d18e26414d6f8f98';

export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {treeData:[], rates:[]};
        this.getItems=this.getItems.bind(this);
        this.getRates=this.getRates.bind(this);
        this.convertPrice=this.convertPrice.bind(this);
        this.removeItem=this.removeItem.bind(this);
        this.updateItem=this.updateItem.bind(this);
        this.createItem=this.createItem.bind(this);
    }

    componentDidMount() {
        this.getItems();
        this.getRates();
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevProps.data !== this.props.data
            &&  prevState.data !== this.state.data) {
            this.getItems();
        }
    }

    convertPrice(price,symbol){
        var rate=this.state.rates[symbol];
        return price * rate;
    }

    removeItem(item){
        if(item.type == "CATEGORY"){
            fetch('http://localhost:8080/api/category/' + item.id, {
                method: 'delete',
            }).then(function(response) {
                return response.ok;
            });
        }else{
            fetch('http://localhost:8080/api/product/' + item.id, {
                method: 'delete',
            }).then(function(response) {
                return response.ok;
            });
        }
    }

    updateItem(item){
        if(item.type == "CATEGORY"){
            fetch('http://localhost:8080/api/category/' + item.id, {
                method: 'put',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(item)
            }).then(function(response) {
                return response.ok;
            });
        }else{
            fetch('http://localhost:8080/api/product/' + item.id, {
                method: 'put',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(item)
            }).then(function(response) {
                return response.ok;
            });
        }
    }

    createItem(item){
        if(item.type == "CATEGORY"){
            fetch('http://localhost:8080/api/category', {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(item)
            }).then(function(response) {
                return response.json();
            }).then(function(data) {
                console.log('created category:', data.id);
            });
        }else{
            fetch('http://localhost:8080/api/product/', {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(item)
            }).then(function(response) {
                return response.json();
            }).then(function(data) {
                console.log('created product:', data.id);
            });
        }
    }

    getItems(){
        fetch('http://localhost:8080/api/items')
            .then(results => {
                return results.json()
            })
            .then(items => {
                this.setState({treeData: getTreeFromFlatData({
                        flatData: items.map(item => ({
                            id:item.id, title: item.name,parent:item.parent,type:item.type,price:item.price,currency:item.currency
                        })),
                        getKey: item => item.id,
                        getParentKey: item => item.parent,
                        rootKey: null,
                    })});
            })
    }

    getRates(){
        fetch('http://data.fixer.io/api/latest' +
            '?access_key='  + ACCESS_KEY)
            .then(results =>{
                return results.json()
            }).then(json => {
            this.setState({
                rates:json.rates
            });
        })
    }


    render() {
        return (
            <div>
                <div style={{ height: 800}}>
                    <SortableTree
                        treeData={this.state.treeData}
                        onChange={treeData => this.setState({ treeData })}
                        generateNodeProps={({ node}) => ({
                            buttons: [
                                <button onClick={()=> {
                                    this.removeItem(node);
                                }}>
                                    Remove
                                </button>,
                                <button onClick={()=> {
                                    this.createItem(node);
                                }}>
                                    Create
                                </button>,
                                <button onClick={()=> {
                                    node.name='test';
                                    this.updateItem(node);
                                }}>
                                    Update
                                </button>,
                            ]
                        })}
                    />
                </div>
            </div>
        );
    }
}

