// @flow
import type {Store as ReduxStore, Dispatch as ReduxDispatch} from 'redux'

export type Id = number

export type Player = {
  +id: Id,
  +name: string,
  +score: number
}

export type List = Array<Player>

export type State = {
  +list: List,
  +isListLoading: boolean
};

export type Action =
    { type: 'REQUEST_LIST'}
  | { type: 'RECEIVE_LIST'}
  ;

export type Store = ReduxStore<State, Action>;

export type Dispatch = ReduxDispatch<Action>;
