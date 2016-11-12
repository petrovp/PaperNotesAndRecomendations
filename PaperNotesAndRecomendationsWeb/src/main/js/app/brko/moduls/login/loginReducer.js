/**
 * Created by ppetrov on 11/03/2016.
 */

import {
    LOGIN_RESPONSE,
} from './loginActions';

import initialState from './initialLoginState';

import { callReducerByMapping } from '../../redux-config/reducers';
import { loadUserTokenFromLocalStorage } from './../../services/local_storage_service'

export default function loginReducer() {
    getAuthTokenIfExist();

    return  callReducerByMapping({
        [LOGIN_RESPONSE]: loginRequestReducer
    }, initialState);
}

function getAuthTokenIfExist() {
    const token = loadUserTokenFromLocalStorage();
    if (token) {
        initialState.token = token;
    }
    return token;
}

function loginRequestReducer(state, action) {
    console.log(action.data);
    return Object.assign({}, state, {
        token:action.data.token
    });
}

export function getTokenFromState(state) {
    return state.common.token;
}
