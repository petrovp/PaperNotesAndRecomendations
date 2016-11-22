/**
 * Created by ppetrov on 10/24/2016.
 */

import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as Actions from './loginActions'

import './login.scss';

import { Form, Button, FormGroup, FormControl } from 'react-bootstrap';

class Login extends React.Component{

    constructor(props) {
        super(props);
        this.user={};
    }

    render() {
        const {
            makeLoginRequest
        } = this.props;

        const handleOnLoginClick = () => {
            console.log(this.user);
            makeLoginRequest(this.user);
        };

        const handleChange = (e) => {
            this.user[e.target.name]=e.target.value;
        }

        return (
            <div className="login-container">
                <div className="container" >
                    <Form className="form">
                        <FormGroup bsSize="sm">
                            <FormControl
                                name="email"
                                type="email"
                                placeholder="Email"
                                onChange={handleChange}
                            />
                        </FormGroup>

                        <FormGroup bsSize="sm">
                            <FormControl
                                name="password"
                                type="password"
                                placeholder="Password"
                                bsSize="sm"
                                onChange={handleChange}
                            />
                        </FormGroup>

                        <Button
                            active={true}
                            bsSize="sm"
                            className="login-button"
                            bsStyle="primary"
                            type="submit"
                            onClick={handleOnLoginClick}
                        >
                            Login
                        </Button>
                    </Form>
                </div>
            </div>
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