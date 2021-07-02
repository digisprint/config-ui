import React from 'react'
import classes from './Card.module.css'
export const Card = (props)=>{

     return(<React.Fragment>{props.items&& props.items.length?<div className="card" style={props.styling}>
  <div>{props.items.map((item,index)=>{
    return <div className="card-body" className={classes.CardItem} onClick={(e)=>{props.setFieldStateByCardItem(e,item)}}  key={index}><h5>{item.title}</h5>
    {/* <img className={classes.cardImage} src={item.imageUrl} alt="book" /> */}
    </div>
  })}</div>
</div>:null}</React.Fragment>)
}