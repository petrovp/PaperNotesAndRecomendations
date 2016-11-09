
import {
    checkStatus,
    parseJSON
} from '../../services/http'

export const SUCCESSFUL_NOTE_SAVING = 'BRKO@LOGIN_MODULE.SUCCESSFUL_NOTE_SAVING';

function successfulSaveNoteRequest(data) {
    console.log(data);
    return {
        type: SUCCESSFUL_NOTE_SAVING,
        data
    }
}

export function saveNoteRequest(noteText, token) {
    console.log(noteText, token);
    return dispatch => {
        fetch('http://localhost:8080/notes/add_note', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'x-auth-token': token
            },
            body: JSON.stringify(noteText)
        })
            .then(checkStatus)
            .then(parseJSON)
            .then(data => {
                dispatch(successfulSaveNoteRequest(data));
            });
    };
}

