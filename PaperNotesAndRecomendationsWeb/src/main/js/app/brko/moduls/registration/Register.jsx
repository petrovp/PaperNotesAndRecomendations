
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

    constructor(props) {
        super(props);
        this.user={};
    }

    render() {
        const {
            tryRegister,
        } = this.props;

        const handleRegisterClick = (props) => {
            console.log(this.user);
            tryRegister(this.user);
        };

        const handleChange = (e) => {
            this.user[e.target.name]=e.target.value;
        }

        return (
            <Form>
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

                <FormGroup bsSize="sm">
                    <FormControl
                        name="firstName"
                        type="text"
                        placeholder="First Name"
                        bsSize="sm"
                        onChange={handleChange}
                    />
                </FormGroup>

                <FormGroup bsSize="sm">
                    <FormControl
                        name="lastName"
                        type="text"
                        placeholder="Last Name"
                        bsSize="sm"
                        onChange={handleChange}
                    />
                </FormGroup>

                <Button active={true} bsSize="sm" bsStyle="primary" onClick={ handleRegisterClick }>
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
