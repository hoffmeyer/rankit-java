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

const toListElement = (player: Player, index: number) => (
  <tr key={player.id}>
    <td>{index + 1}</td>
    <td>{player.name}</td>
    <td>{player.points}</td>
    <td>{winsLossesToString(player.currentWinsInRow, player.currentLossesInRow)}</td>
  </tr>
)

export const List = ({list}: State) => (
    <table>
      <thead>
        <tr>
          <td>No</td>
          <td>Player</td>
          <td>Score</td>
          <td>Streak</td>
        </tr>
      </thead>
      <tbody>
        {list.map(toListElement)}
      </tbody>
    </table>
)
