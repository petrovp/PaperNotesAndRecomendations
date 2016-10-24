
export const LOGIN_REQUEST = 'BRKO@LOGIN_MODULE.LOGIN_REQUEST';

export function makeLoginRequest(email, password) {
    return {
        type: LOGIN_REQUEST,
        email,
        password
    };
}