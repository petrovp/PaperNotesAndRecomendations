
import {
    checkStatus,
    parseJSON
} from '../../services/http'

import {
    getTokenFromState
} from './../login/loginReducer'

export const SUCCESSFUL_NOTE_SAVING = 'BRKO@LOGIN_MODULE.SUCCESSFUL_NOTE_SAVING';
export const SUCCESSFUL_NOTE_FETCHING = 'BRKO@LOGIN_MODULE.SUCCESSFUL_NOTE_FETCHING';


function successfulSaveNoteRequest(data) {
    return {
        type: SUCCESSFUL_NOTE_SAVING,
        data
    }
}

function successfulGetNoteRequest(data) {
    return {
        type: SUCCESSFUL_NOTE_FETCHING,
        data
    }
}

export function saveNoteRequest(noteText, token) {
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

export function asyncFetchNotes() {
    return (dispatch, getState) => {
        const state = getState();
        const token = getTokenFromState(state);
        fetch('http://localhost:8080/notes/all', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'x-auth-token': token
            }
        })
            .then(checkStatus)
            .then(parseJSON)
            .then(data => {
                dispatch(successfulGetNoteRequest(data));
            });
    };
}

