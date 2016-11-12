
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Scrollbars } from 'react-custom-scrollbars';

import * as Actions from './notesActions'

import {
    Form,
    Button,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

import { Card, CardTitle, CardText, CardActions } from 'react-toolbox/lib/card';

import {
    getTokenFromState
} from './../login/loginReducer'

import {
    getNotesFromState
} from './notesReducer'

class Notes extends React.Component {

    constructor(props) {
        super(props);
        this.noteText='';
    }

    componentDidMount() {
        this.props.asyncFetchNotes();
    }

    render() {

        const {
            saveNoteRequest,
            notes
        } = this.props;

        const handleChange = (e) => {
            this.noteText=e.target.value;
        };

        const handleSaveNoteButtonClick = () => {
            saveNoteRequest(this.noteText);
        };

        const renderNotes = () =>
            notes.map(note =>
                <Card
                    style={{width: '350px'}}
                    key={note.id}
                >
                    <CardTitle
                        title="Note"
                        subtitle={note.createdOn}
                    />
                    <CardText>{note.text}</CardText>
                    <CardActions >
                        <Button bsStyle="primary" > Edit </Button>
                        <Button bsStyle="primary" > Share </Button>
                    </CardActions>
                </Card>
            )

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

                <div>
                    <Scrollbars
                        thumbMinSize={10}
                        style={{ width: 500, height: 300 }}
                    >
                        <div>
                            {renderNotes()}
                        </div>
                    </Scrollbars>
                </div>
            </div>
        )
    }
}

Notes.propTypes = {
    saveNoteRequest: React.PropTypes.func,
    notes: React.PropTypes.array,
    asyncFetchNotes: React.PropTypes.func,
};

function mapStateToProps(state) {
    return {
        notes: getNotesFromState(state)
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(Actions, dispatch);
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Notes);
