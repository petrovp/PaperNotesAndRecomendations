/**
 * Created by ppetrov on 11/10/2016.
 */

import {
    SUCCESSFUL_NOTE_SAVING,
    SUCCESSFUL_NOTE_FETCHING
} from './notesActions';

import initialState from './notesInitialState';

import { callReducerByMapping } from '../../redux-config/reducers';

export default function notesReducer() {
    return  callReducerByMapping({
        [SUCCESSFUL_NOTE_SAVING]: noteSavingReducer,
        [SUCCESSFUL_NOTE_FETCHING]: noteFetchingReducer,
    }, initialState);
}

function noteSavingReducer(state, action) {

}

function noteFetchingReducer(state, action) {

}

export function getNotesFromState(state) {
    return state.notes;
}
