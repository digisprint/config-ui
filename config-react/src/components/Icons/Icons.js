import React from 'react'
import classes from './Icons.module.css'
export const Icon = (props)=>{
    return <span className={classes.Icons} onClick={props.handleOpen}>{props.children}</span>
}