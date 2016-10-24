import React from 'react';

import {
    Form,
    Button,
    FormGroup,
    FormControl,
    Checkbox,
    ControlLabel
} from 'react-bootstrap';

class Register extends React.Component {

    render() {
        const {
            categories
        } = this.props;

        const renderCategoriesCheckboxes = () =>
            categories.map(category => <Checkbox  bsClass="sm" > {category} </Checkbox>)

        return (
            <Form>
                <FormGroup bsSize="sm">
                    <FormControl type="email" placeholder="Email" bsSize="sm"/>
                </FormGroup>

                <FormGroup bsSize="sm">
                    <FormControl type="password" placeholder="Password" bsSize="sm"/>
                </FormGroup>

                <FormGroup bsSize="sm" >
                    <ControlLabel> Categories from interest </ControlLabel>
                    {renderCategoriesCheckboxes}
                </FormGroup>

                <Button active={true} bsSize="sm" bsStyle="primary" type="submit">
                    Register
                </Button>
            </Form>
        );
    }
}

Register.defaultProps = {
    categories: [
        'Category 1',
        'Category 2',
        'Category 3',
        'Category 4',
        'Category 5'
    ]
};

Register.propTypes = {
    categories: React.PropTypes.array,
};

export default Register;
