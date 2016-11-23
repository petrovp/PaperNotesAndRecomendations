/**
 * Created by ppetrov on 10/14/2016.
 */

import React from 'react';
import { Provider } from 'react-redux';
import { persistStore } from 'redux-persist'
import {  Router, Route, IndexRoute, browserHistory } from 'react-router';

import configureStore from '../brko/redux-config/store';

import Demo from './demo/ui/Demo';
import Home from './moduls/home/Home';
import Login from './moduls/login/Login';
import Register from './moduls/registration/Register';
import Notes from './moduls/notesAndPapers/NotesAndPapers'

const store = configureStore(browserHistory);
const appRootUrl = "/";

class App extends React.Component{

    render() {
        store.subscribe(()=>{
            localStorage.setItem('reduxState_USER_TOKEN', JSON.stringify(store.getState().common.token));
        });

        return (
            <Provider store={store}>
                <Router history={browserHistory}>
                    <Route path={appRootUrl}>
                        <IndexRoute component={Home}/>
                        <Route path={appRootUrl + "demo"} component={Demo}/>
                        <Route path={appRootUrl + "login"} component={Login}/>
                        <Route path={appRootUrl + "register"} component={Register}/>
                        <Route path={appRootUrl + "notes"} component={Notes}/>
                    </Route>
                </Router>
            </Provider>
        )
    }
};

export default App;
