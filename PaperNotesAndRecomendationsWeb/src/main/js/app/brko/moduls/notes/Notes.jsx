
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as Actions from './notesActions'

import {
    Form,
    Button,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

import {
    getTokenFromState
} from './../login/loginReducer'

class Notes extends React.Component {

    constructor(props) {
        super(props);
        this.noteText='';
    }

    render() {

        const {
            token,
            saveNoteRequest,
        } = this.props;

        const handleChange = (e) => {
            this.noteText=e.target.value;
        };

        const handleSaveNoteButtonClick = () => {
            saveNoteRequest(this.noteText, token);
        };

        return (
            <div className="notes_container">

                <Form>
                    <FormGroup bsSize="sm">
                        <FormControl
                            name="email"
                            type="email"
                            placeholder="Email"
                            onChange={handleChange}
                        />
                    </FormGroup>

                    <Button active={true} bsSize="sm" bsStyle="primary" onClick={ handleSaveNoteButtonClick }>
                        Save Note
                    </Button>
                </Form>
            </div>
        )
    }
}


function mapStateToProps(state) {
    return {
        token: getTokenFromState(state)
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(Actions, dispatch);
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Notes);
