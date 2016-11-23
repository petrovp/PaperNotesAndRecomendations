/**
 * Created by ppetrov on 10/14/2016.
 */

import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';

import demoReducer from '../demo/reducers/demoRootReducer';
import registrationReducer from '../moduls/registration/registraionReducer'
import loginReducer from '../moduls/login/loginReducer'
import notesReducer from '../moduls/notesAndPapers/notesAndPapersReducer'

export const rootReducer = combineReducers({
  demo: demoReducer(),
  registration: registrationReducer(),
  common:loginReducer(),
  notes_state: notesReducer(),
  routing: routerReducer
});

export function callReducerByMapping(mappings, initialState) {
  if (!initialState) {
    throw new Error('Error - every reducer should have initial state.');
  }

  return function configuredReducer(state = initialState, action) {
    const reducer = mappings[action.type];
    if (reducer) {
      return reducer(state, action);
    }
    return state;
  };
}
