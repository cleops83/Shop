import 'react-sortable-tree/style.css';
import React from 'react';
import SortableTree, {
    addNodeUnderParent,
    getTreeFromFlatData, removeNodeAtPath,
} from 'react-sortable-tree';

const ACCESS_KEY = '4007203118c696a1d18e26414d6f8f98';

export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {treeData:[], rates:[]};
        this.convertPrice=this.convertPrice.bind(this);
    }

    convertPrice(price,symbol){
        if(symbol =='USD'){
            var rate=this.state.rates.USD;
            return price * rate;
        }
        return price;
    }

    componentDidMount() {
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
                                <button onClick={()=>
                                    alert('price' + node.price + ' to ' + this.convertPrice(node.price, "USD"))
                                }>
                                    Remove
                                </button>,
                            ],
                            title: (
                                <input style={{ fontSize: '1.1rem' }} value={node.title}
                                       onChange={event => {
                                           const title = event.target.value;
                                           alert(title);
                                       }}
                                />),
                        })}
                    />
                </div>
            </div>
        );
    }
}

