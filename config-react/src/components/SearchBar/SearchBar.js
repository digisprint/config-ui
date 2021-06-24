import React from 'react'
import { BsSearch } from "react-icons/bs";

export const SearchBar = (props)=>{
    return(<React.Fragment>
        <div className="input-group mb-3 w-3 container" style={{'padding': '30px', 'border-bottom': '2px solid #eee'}}>
          <input type="text" className="form-control" placeholder="Search the Item" 
           aria-label="Recipient's username" aria-describedby="basic-addon2" 
           value={props.fieldState} onChange={e=>{return props.fieldChanging(e)}}
           />
          <div className="input-group-append" style={{cursor:'pointer'}}>
            <span className="input-group-text" id="basic-addon2"><BsSearch/></span>
          </div>
        </div>
    </React.Fragment>)
}