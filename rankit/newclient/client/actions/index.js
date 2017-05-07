// @flow
import type {List, Action, Dispatch} from '../types'

export const REQUEST_LIST = 'REQUEST_LIST';
export const RECEIVE_LIST = 'RECEIVE_LIST';

export const requestList = () => ({
  type: REQUEST_LIST
});

export const receiveList = (list: List) => ({
  type: RECEIVE_LIST,
  list: list
});

export const fetchList = (dispatch: Dispatch) => {
  dispatch(requestList);
  fetch('http://localhost:8338/api/list')
  .then( response => response.json())
  .then( json => dispatch( receiveList(json)));
}
