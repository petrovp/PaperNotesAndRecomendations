
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Scrollbars } from 'react-custom-scrollbars';

import * as Actions from './notesAndPapersActions'
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
    getNotesFromState,
    getPapersFromState
} from './notesAndPapersReducer'

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
            notes,
            papers
        } = this.props;

        const handleChange = (e) => {
            this.noteText=e.target.value;
        };

        const handleSaveNoteButtonClick = () => {
            saveNoteRequest(this.noteText);
        };

        const renderPapers = () =>
            papers.map(paper =>
                <Card
                    className="card note-card"
                    style={{width: '570px'}}
                    key={paper.id}
                >
                    <CardTitle
                        className="cardTitle"
                        title={paper.category.name}
                        subtitle={paper.title}
                    />
                    <CardText
                        className="cardText"
                    >
                        {paper.abstractContent}
                    </CardText>
                </Card>
            );

        const getPartForSharedNote = (note) => {
            return;
        };

        const renderNotes = () =>
            notes.map(note =>
                <Card
                    className="card note-card"
                    style={{width: '470px'}}
                    key={note.id}
                >
                    {getPartForSharedNote(note)}
                    <CardTitle
                        className="cardTitle"
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
            <div className="main-container" >
                <div className="notes_container container">

                    <Form className="notes-form">
                        <FormGroup bsSize="sm">
                            <FormControl
                                name="new_note"
                                type="text"
                                style={{ height: 70 }}
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

                    <div className="notes-scrollbar-container">
                        <Scrollbars
                            thumbMinSize={10}
                            style={{ width: 500, height: 700 }}
                        >
                            <div>
                                {renderNotes()}
                            </div>
                        </Scrollbars>
                    </div>
                    <div className="papers-scrollbar-container">
                        <Scrollbars
                            thumbMinSize={10}
                            style={{ width: 600, height: 700 }}
                        >
                            <div>
                                {renderPapers()}
                            </div>
                        </Scrollbars>
                    </div>
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
        notes: getNotesFromState(state),
        papers: getPapersFromState(state)
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(Actions, dispatch);
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Notes);
