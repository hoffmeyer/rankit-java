// @flow
import React from 'react'
import type { State, Player } from '../types'

const toListElement = (player: Player) => (
  <tr key={player.id}>
    <td>{player.name}</td>
    <td>{player.points}</td>
  </tr>
)

const List = ({list}: State) => (
  <div>
    <h1>The List!</h1>
    <table>
      <thead>
        <tr>
          <td>Player</td>
          <td>Score</td>
        </tr>
      </thead>
      <tbody>
        {list.map(toListElement)}
      </tbody>
    </table>
  </div>
)

export default List;
