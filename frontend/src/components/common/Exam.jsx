import React, { Fragment, useState } from "react";
import { useNavigate } from "react-router-dom";
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import CropFreeIcon from '@mui/icons-material/CropFree';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import CoPresentIcon from '@mui/icons-material/CoPresent';
import PeopleIcon from '@mui/icons-material/People';
import SignpostIcon from '@mui/icons-material/Signpost';

export default function TemporaryDrawer() {
  const [state, setState] = useState({
    top: false,
    left: false,
    bottom: false,
    right: false,
  });
  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setState({ ...state, [anchor]: open });
  };
  const navigate = useNavigate();
  const toRegister = () => {
    navigate("/room/register");
  };
  const toRegBoast = () => {
    navigate("/room/boastReg");
  }
  const toBoastRoom =() => {
    navigate("/room/boastlist");
  };
  const toRoomList = () => {
    navigate("/room/roomlist");
  };
  const toMap = () => {
    navigate("/room/map");
  };
  const list = (anchor) => (
    <Box
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {[['내방팔기', toRegister], ['내방사기', toRoomList], ['자랑하기', toRegBoast], ['니방보기', toBoastRoom]].map(([text, loc], index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton onClick={loc}>
              <ListItemIcon>
                {index % 4 < 2 ? <HomeOutlinedIcon sx={{ color: "#2C4B48", fontSize: 48}} /> : <CoPresentIcon sx={{ color: "#2C4B48", fontSize: 48}} />}
              </ListItemIcon>
              &nbsp;
              <ListItemText primary={text} sx={{ color: "#2C4B48" }} primaryTypographyProps={{ fontSize: 36, fontFamily: "SeoulHangangB" }} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <Divider />
      <List>
        {[['지도보기', toMap]].map(([text, loc], index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton onClick={loc}>
              <ListItemIcon>
                {index % 3 === 0 ? <SignpostIcon sx={{ color: "#2C4B48", fontSize: 48}} /> : <PeopleIcon sx={{ color: "#2C4B48", fontSize: 48}} /> }
              </ListItemIcon>
              &nbsp;
              <ListItemText primary={text} sx={{ color: "#2C4B48" }} primaryTypographyProps={{ fontSize: 36, fontFamily: "SeoulHangangB" }} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Box>
  );
  return (
    <div>
      {['left'].map((anchor) => (
        <Fragment key={anchor}>
          <CropFreeIcon sx={{ width: 58, height: 58, color: "#2C4B48" }} onClick={toggleDrawer(anchor, true)} />
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
          >
            {list(anchor)}
          </Drawer>
        </Fragment>
      ))}
    </div>
  );
}