import React from 'react'
import {Icon} from '../../components/Icons/Icons'
import {TableBody} from '../../components/TableBody/TableBody'
import {TableColumn} from '../../components/TableColumn/TableColumn'
import {TableHead} from '../../components/TableHead/TableHead'
import {TableHeadCol} from '../../components/TableHeadCol/TableHeadCol'
import {TableHeadRow} from '../../components/TableHeadRow/TableHeadRow';
import { BsPencil,BsFillTrashFill,BsClipboardData } from "react-icons/bs";

export const TableData=(props)=>{
    return (<table className="table table-hover border" style={{width:'50%'}}>
        <TableHead>
            <TableHeadRow>
                <TableHeadCol>S.No</TableHeadCol>
                <TableHeadCol>First Name</TableHeadCol>
                <TableHeadCol>Last Name</TableHeadCol>
                <TableHeadCol>Operations</TableHeadCol>
            </TableHeadRow>
        </TableHead>
        <TableBody>
            <TableHeadRow>
                <TableColumn>1</TableColumn>
                <TableColumn>Teja</TableColumn>
                <TableColumn>Yerraguntla</TableColumn>
                <TableColumn>
                    <Icon handleOpen = {props.handleOpen}><BsPencil/></Icon>
                    <Icon><BsFillTrashFill/></Icon>
                    <Icon><BsClipboardData /></Icon>
                </TableColumn>

            </TableHeadRow>
            <TableHeadRow>
                <TableColumn>2</TableColumn>
                <TableColumn>Nagendra</TableColumn>
                <TableColumn>Yerraguntla</TableColumn>
                <TableColumn>
                    <Icon  handleOpen = {props.handleOpen}><BsPencil/></Icon>
                    <Icon><BsFillTrashFill/></Icon>
                    <Icon><BsClipboardData /></Icon>
                </TableColumn>
            </TableHeadRow>
            <TableHeadRow>
                <TableColumn>3</TableColumn>
                <TableColumn>RamaLakshmi</TableColumn>
                <TableColumn>Yerraguntla</TableColumn>
                <TableColumn>
                    <Icon  handleOpen = {props.handleOpen}><BsPencil/></Icon>
                    <Icon><BsFillTrashFill/></Icon>
                    <Icon><BsClipboardData /></Icon>
                </TableColumn>
            </TableHeadRow>
        </TableBody>
    </table>)
}