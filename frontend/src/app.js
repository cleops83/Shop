import React from 'react';

export default class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {categories: []};
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/categories')
            .then(results => {
                return results.json()
            })
            .then(categories => {
                this.setState({categories});
            })
    }

    render() {
        return (
            <CategoryList categories={this.state.categories}/>
        )
    }
}

class CategoryList extends React.Component{
    render() {
        var categories = this.props.categories.map(category =>
            <Category key={category.id} category={category}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
                {categories}
                </tbody>
            </table>
        )
    }
}

class Category extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.category.id}</td>
                <td>{this.props.category.name}</td>
            </tr>
        )
    }

}