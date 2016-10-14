/**
 * Created by ppetrov on 10/14/2016.
 */

import React from 'react';
import Loader from 'react-loader';

export default React.createClass({
    displayName: "Application",

    getInitialState() {
        return {
            loaded: false,
            name: null
        }
    },

    componentDidMount() {
        window.setTimeout(() => {
            this.setState({
                loaded: true,
                name: 'World'
            })
        }, 3000);
    },

    render() {
        return (
            <Loader loaded={this.state.loaded}>
                {'Hello ' + this.state.name}
            </Loader>
        )
    }
});