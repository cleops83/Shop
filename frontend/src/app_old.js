import React from 'react';

export default class App_old extends React.Component {

    constructor(props) {
        super(props);
        this.state = {items: []};
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/items')
            .then(results => {
                return results.json()
            })
            .then(items => {
                this.setState({items});
            })
    }

    render() {
        return (
            <ItemList items={this.state.items}/>
        )
    }
}

const generateKey = () => {
    return '_' + Math.random().toString(36).substr(2, 9);
}


class ItemList extends React.Component{
    render() {
        var items = this.props.items.map(item =>
            <Item key={generateKey()} item={item}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Type</th>
                </tr>
                {items}
                </tbody>
            </table>
        )
    }
}

class Item extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.item.id}</td>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.type}</td>
            </tr>
        )
    }

}