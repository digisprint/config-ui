import React,{useState} from 'react'
import { TableData } from '../../container/TableData/TableData';
import {TypeaHead} from '../../container/TypeaHead/TypeaHead';
import {ModalContainer} from '../../container/Modals/Modals'
export const Home = ()=>{
    const [modalState,setModalState] = useState(false);

    const handleOpen = () => {
        setModalState(true)
      };
    
      const handleClose = () => {
        setModalState(false)
      };
    return (<React.Fragment>
        <TypeaHead />
        <TableData handleOpen={handleOpen}/>
        <ModalContainer modalState = {modalState} handleClose = {handleClose}/>
    </React.Fragment>)
}