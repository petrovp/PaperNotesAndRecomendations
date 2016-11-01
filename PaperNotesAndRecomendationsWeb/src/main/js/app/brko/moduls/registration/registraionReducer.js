/**
 * Created by ppetrov on 10/14/2016.
 */

import {
    UPDATE_CATEGORY_CHECKED,
} from './registrationActions';

import initialState from './initialRegistraionState';

import { callReducerByMapping } from '../../redux-config/reducers';

export default function registrationReducer() {
    return  callReducerByMapping({
        [UPDATE_CATEGORY_CHECKED]: updateCategoryCheckedReducer
    }, initialState);
}

function updateCategoryCheckedReducer(state, action) {
    const newCategories = [];
    state.categories.map(category => {
        if (category.id === action.categoryId) {
            category.isChecked=!category.isChecked;
        }
        newCategories.push(category);
    });

    return Object.assign({}, state, {
        categories:newCategories
    });
}

function getRegistrationState(state) {
    return state.registration;
}

export function getCategories(state) {
    return getRegistrationState(state).categories;
}
