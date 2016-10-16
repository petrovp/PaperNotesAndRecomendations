/**
 * Created by ppetrov on 10/14/2016.
 */

import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';

import demoReducer from '../demo/reducers/demoRootReducer';

export const rootReducer = combineReducers({
  demo: demoReducer(),
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
