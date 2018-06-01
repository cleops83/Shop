import 'react-sortable-tree/style.css';
import React from 'react';
import SortableTree, {
    getTreeFromFlatData,
} from 'react-sortable-tree';


export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {treeData:[]};
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/items')
            .then(results => {
                return results.json()
            })
            .then(items => {
                this.setState({treeData: getTreeFromFlatData({
                        flatData: items.map(item => ({
                            id:item.id, title: item.name,parent:item.parent
                        })),
                        getKey: item => item.id,
                        getParentKey: item => item.parent,
                        rootKey: null,
                    })});
            })
    }


    render() {
        return (
            <div>
                <div style={{ height: 800}}>
                    <SortableTree
                        treeData={this.state.treeData}
                        onChange={treeData => this.setState({ treeData })}
                    />
                </div>
            </div>
        );
    }
}