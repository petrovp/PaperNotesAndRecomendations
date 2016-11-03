/**
 * Created by ppetrov on 11/03/2016.
 */

import {
    LOGIN_RESPONSE,
} from './loginActions';

import initialState from './initialLoginState';

import { callReducerByMapping } from '../../redux-config/reducers';

export default function loginReducer() {
    return  callReducerByMapping({
        [LOGIN_RESPONSE]: loginRequestReducer
    }, initialState);
}

function loginRequestReducer(state, action) {
    console.log(action.data);
    return Object.assign({}, state, {
        token:action.data.token
    });
}
