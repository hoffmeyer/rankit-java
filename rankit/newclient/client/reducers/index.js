// @flow
import {combineReducers} from 'redux';
import {
  REQUEST_LIST,
  RECEIVE_LIST,
  REQUEST_MATCHES,
  RECEIVE_MATCHES
} from '../actions'
import type {
  State,
  Action,
  List,
  Match
} from '../types';

function matches(state: Array<Match> = [], action: Action): Array<Match> {
  switch (action.type){
    case REQUEST_MATCHES:
      return state
    case RECEIVE_MATCHES:
      return action.matches
    default:
      return state
  }
}

function list(state: List = [] , action: Action): List  {
  switch (action.type){
    case REQUEST_LIST:
      return state
    case RECEIVE_LIST:
      return action.list
    default:
      return state
  }
}

function isListLoading (state = false, action: Action): boolean {
  switch (action.type){
    case REQUEST_LIST:
      console.log('List is now loading')
      return true
    case RECEIVE_LIST:
      console.log('List finished loading')
      return false
    default:
      return state;
  }
}

function isMatchesLoading (state = false, action: Action): boolean {
  switch (action.type){
    case REQUEST_MATCHES:
      console.log('Matches is loading')
      return true;
    case RECEIVE_MATCHES:
      console.log('Matches finished loading')
      return false;
    default:
      return state;
  }
}

const rootReducer = combineReducers({
  list,
  matches,
  isListLoading,
  isMatchesLoading
});

export default rootReducer;
