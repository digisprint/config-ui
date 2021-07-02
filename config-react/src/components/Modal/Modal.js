import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';

const useStyles = makeStyles((theme) => ({
  paper: {
    position: 'absolute',
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
}));

export const Modals=(props)=> {
  const classes = useStyles();
  // getModalStyle is not a pure function, we roll the style only on the first render
  // const [open, setOpen] = React.useState(false);

  

  return (
    <div>
      <Modal
        open={props.modalState}
        onClose={props.handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        style={{display:'flex',alignItems:'center',justifyContent:'center'}}
      >
          <div className={classes.paper}><div>{props.children}</div>
          <hr/>
          <div style={{display:'flex',justifyContent:'space-between'}}>
          <button onClick={props.handleClose} className="btn">Close</button>
          <button onClick={props.handleClose} className="btn">Ok</button>
          </div>
          </div>
      </Modal>
    </div>
  );
}