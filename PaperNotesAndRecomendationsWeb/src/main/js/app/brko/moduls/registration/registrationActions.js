
import 'whatwg-fetch';

export const UPDATE_CATEGORY_CHECKED = 'BRKO@REGISTRAION_MODULE.UPDATE_CATEGORY_CHECKED';
export const REGISTRATION_RESULT_FETCHED = 'BRKO@REGISTER.REGISTRATION_RESULT_FETCHED';


export function updateCategoryChecked(categoryId) {
    return {
        type: UPDATE_CATEGORY_CHECKED,
        categoryId
    };
}

export function tryRegister(user) {
    console.log(JSON.stringify(user));
    return dispatch => {
        fetch('http://localhost:8080/register', {
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