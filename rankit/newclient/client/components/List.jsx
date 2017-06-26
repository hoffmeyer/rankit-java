// @flow
import React from 'react'
import type { State, Player } from '../types'

const winsLossesToString = (wins: number, losses: number) => {
  if( wins > 0 ){
    return '↑' + wins
  } else if(losses > 0){
    return '↓' + losses
  } else return ''
}

const toListElement = (player: Player) => (
  <tr key={player.id}>
    <td>{player.name}</td>
    <td>{player.points}</td>
    <td>{winsLossesToString(player.currentWinsInRow, player.currentLossesInRow)}</td>
  </tr>
)

export const List = ({list}: State) => (
  <div>
    <h1>The List!</h1>
    <table>
      <thead>
        <tr>
          <td>Player</td>
          <td>Score</td>
          <td>Streak</td>
        </tr>
      </thead>
      <tbody>
        {list.map(toListElement)}
      </tbody>
    </table>
  </div>
)
