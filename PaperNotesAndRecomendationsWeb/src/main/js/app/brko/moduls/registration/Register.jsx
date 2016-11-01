
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as Actions from './registrationActions'
import { getCategories } from './registraionReducer'

import {
    Form,
    Button,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

import {
    Checkbox
} from 'rc-checkbox'

class Register extends React.Component {

    render() {
        const {
            tryRegister,
            categories,
            updateCategoryChecked
        } = this.props;

        const checkForEmptyField = (user) =>
            user.email.length !==0 && user.password.length !==0 && user.firstName.length !==0 && user.lastName.length !==0;

        const handleRegisterClick = (props) => {
            user = {
                email: props.email.value,
                password: props.password.value
            }

            tryRegister(user);
        }

        return (
            <Form>
                <FormGroup bsSize="sm">
                    <FormControl
                        name="email"
                        type="email"
                        placeholder="Email"
                        bsSize="sm"
                        ref={(email) => { this.email = email; }}
                    />
                </FormGroup>

                <FormGroup bsSize="sm">
                    <FormControl
                        name="password"
                        type="password"
                        placeholder="Password"
                        bsSize="sm"
                        ref={(password) => { this.password = password; }}
                    />
                </FormGroup>

                <FormGroup bsSize="sm">
                    <FormControl
                        name="firstName"
                        type="text"
                        placeholder="First Name"
                        bsSize="sm"
                        ref={(firstName) => { this.firstName = firstName; }}
                    />
                </FormGroup>

                <FormGroup bsSize="sm">
                    <FormControl
                        name="lastName"
                        type="text"
                        placeholder="Last Name"
                        bsSize="sm"
                        ref={(lastName) => { this.lastName = lastName; }}
                    />
                </FormGroup>

                <Button active={true} bsSize="sm" bsStyle="primary" onClick={handleRegisterClick(
                    {
                        email: this.email.value,
                        firstName:this.firstName.value
                    })}>
                    Register
                </Button>

            </Form>
        );
    }
}

Register.propTypes = {
    tryRegister: React.PropTypes.func,
    categories: React.PropTypes.array,
    updateCategoryChecked: React.PropTypes.func
};

function mapStateToProps(state) {
    return {
      categories: getCategories(state),
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(Actions, dispatch);
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Register);
