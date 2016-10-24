/**
 * Created by ppetrov on 10/24/2016.
 */

import React from 'react';

import { Form, Button, FormGroup, FormControl } from 'react-bootstrap';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as Actions from './loginActions'

class Login extends React.Component{

    render() {
        const {
            makeLoginRequest
        } = this.props;

        const handleOnLoginClick = () => {
            const email = document.getElementsByName("emailInput").valueOf();
            const password = document.getElementsByName("passwordInput").valueOf();
            return makeLoginRequest(email, password);
        };

        return (
            <Form>
                <FormGroup>
                    <FormControl type="email" placeholder="Email" name="emailInput"/>
                </FormGroup>

                <FormGroup>
                    <FormControl type="password" placeholder="Password" name="passwordInput"/>
                </FormGroup>

                <Button active={true} bsSize="sm" bsStyle="primary" type="submit" onClick={handleOnLoginClick}>
                    Login
                </Button>
            </Form>
        );
    }
}



function mapDispatchToProps(dispatch) {
    const allActionCreators = Object.assign(
        Actions
    );
    return bindActionCreators(allActionCreators, dispatch);
}

export default connect(
    null,
    mapDispatchToProps
)(Login);