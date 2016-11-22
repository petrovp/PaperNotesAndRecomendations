
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Scrollbars } from 'react-custom-scrollbars';

import * as Actions from './notesActions'
import './notes.scss'
import 'react-toolbox/lib/card/theme.scss';

import {
    Form,
    Button,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

import { Card, CardTitle, CardText, CardActions } from 'react-toolbox/lib/card';

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

        const getPartForSharedNote = (note) => {
            if (note.id === '5834c9be890e0e1f14b279a7') {
                return (
                    <div className="shared-from">
                        Shared from ppetrov.
                    </div>
                )
            }
        }

        const renderNotes = () =>
            notes.map(note =>
                <Card
                    className="card note-card"
                    style={{width: '450px'}}
                    key={note.id}
                >
                    {getPartForSharedNote(note)}
                    <CardTitle
                        className="cardTitle"
                        title="Graduation note"
                        subtitle={new Date(note.createdOn).toDateString()}
                    />
                    <CardText
                        className="cardText"
                    >
                        {note.text}
                    </CardText>
                    <CardActions className="cardActions">
                        <Button bsStyle="primary" className="action-button" > Edit </Button>
                        <Button bsStyle="primary" className="action-button" > Share </Button>
                    </CardActions>
                </Card>
            )

        return (
            <div className="notes_container container">

                <Form className="notes-form">
                    <FormGroup bsSize="sm">
                        <FormControl
                            name="new_note"
                            type="text"

                            placeholder="Note..."
                            onChange={handleChange}
                        />
                    </FormGroup>

                    <Button
                        className="save-note-button"
                        active={true}
                        bsSize="sm"
                        bsStyle="primary"
                        onClick={ handleSaveNoteButtonClick }>
                        Save Note
                    </Button>
                </Form>

                <div>
                    <Scrollbars
                        thumbMinSize={10}
                        style={{ width: 500, height: 600 }}
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
