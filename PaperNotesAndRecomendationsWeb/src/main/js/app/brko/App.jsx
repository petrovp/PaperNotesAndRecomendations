/**
 * Created by ppetrov on 10/14/2016.
 */

import React from 'react';
import { Provider } from 'react-redux';
import {  Router, Route, IndexRoute, browserHistory } from 'react-router';

import configureStore from '../brko/redux-config/store';

import Demo from './demo/ui/Demo';
import Home from './moduls/home/Home';
import Login from './moduls/login/Login';
import Register from './moduls/registration/Register';

const store = configureStore(browserHistory);
const appRootUrl = "/";

export default function App() {
    return (
        <Provider store={store}>
            <Router history={browserHistory}>
                <Route path={appRootUrl} >
                    <IndexRoute component={Home} />
                    <Route path={appRootUrl+"demo"} component={Demo}/>
                    <Route path={appRootUrl+"login"} component={Login}/>
                    <Route path={appRootUrl+"register"} component={Register}/>

                </Route>
            </Router>
        </Provider>
    )
}
