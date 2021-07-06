import React,{useState, useMemo} from 'react'
import { BsSearch } from "react-icons/bs";
import DataList from '../../templates/UI/DataList';

function SearchBarStatic(props){
  const [input, setInput] = useState('')

   //search functionality
    const handleSearchChange = (e) => {
        setInput(e.target.value)
    }

    if(props.list && props.list.body.length>0){
      props.list.body = props.list.body.filter((ele)=>{
        return ele.key.toLowerCase().match(input.toLowerCase())
        })
        console.log('search',props.list.body)
    }
    return(
    
    <React.Fragment>
      {props.list && props.list.body.length === 0 ? (
        <div className="input-group mb-3 w-3 container" style={{'padding': '30px', 'border-bottom': '2px solid #eee'}}>

        <input type="text" className="form-control" placeholder="Search by Key" 
         value={input} onChange={handleSearchChange}
         />
        <div className="input-group-append" style={{cursor:'pointer'}}>
          <span className="input-group-text" id="basic-addon2"><BsSearch/></span>
        </div>
        <h1>No result found</h1>
      </div>
      ) : (
<div className="input-group mb-3 w-3 container" style={{'padding': '30px', 'border-bottom': '2px solid #eee'}}>

          <input type="text" className="form-control" placeholder="Search by Key" 
           value={input} onChange={handleSearchChange}
           />
          <div className="input-group-append" style={{cursor:'pointer'}}>
            <span className="input-group-text" id="basic-addon2"><BsSearch/></span>
          </div>
          
          {/* {props.list && props.list.body.map((ele)=> {
            // {console.log('abcd',{...ele})}
            return (
              <DataList  {...ele}/>
            )
          })} */}
        </div>
      )} 
        
    </React.Fragment>)
}

export default SearchBarStatic