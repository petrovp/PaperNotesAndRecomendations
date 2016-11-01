
export const LOGIN_REQUEST = 'BRKO@LOGIN_MODULE.LOGIN_REQUEST';

export function makeLoginRequest(user) {
    console.log(JSON.stringify(user));
    return dispatch => {
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then(response => {
            console.log(response);
        });
    };
}