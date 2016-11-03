
import {
    checkStatus,
    parseJSON
} from '../../services/http'

export const LOGIN_RESPONSE = 'BRKO@LOGIN_MODULE.LOGIN_RESPONSE';

function successfulLoginRequest(data) {
    return {
        type: LOGIN_RESPONSE,
        data
    }
}

export function makeLoginRequest(user) {
    return dispatch => {
        fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
            .then(checkStatus)
            .then(parseJSON)
            .then(data => {
                dispatch(successfulLoginRequest(data));
            });
    };
}

