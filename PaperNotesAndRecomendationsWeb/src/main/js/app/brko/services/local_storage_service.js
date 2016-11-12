
export function loadUserTokenFromLocalStorage() {
    return localStorage.getItem('reduxState_USER_TOKEN')
        ? JSON.parse(localStorage.getItem('reduxState_USER_TOKEN'))
        : '';
}