// @flow
import type {Store as ReduxStore, Dispatch as ReduxDispatch} from 'redux'

export type State = {
  +count: number
};

export type Action =
    { type: 'INCREMENT' }
  | { type: 'DECREMENT'}
  ;

export type Store = ReduxStore<State, Action>;

export type Dispatch = ReduxDispatch<Action>;
