import Names from '../constants/names';

export const getCurrentJwtToken = () => {
  // console.log('### ' + localStorage.getItem(Names.JWT_TOKEN));
  return localStorage.getItem(Names.JWT_TOKEN);
};

const combined = {
  getCurrentJwtToken,
};

export default combined;
