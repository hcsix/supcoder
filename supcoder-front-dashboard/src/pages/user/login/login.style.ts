import { createStyles } from 'antd-style';

const useStyles = createStyles(({ token }) => {
  return {
    action: {
      marginLeft: '8px',
      color: 'rgba(0, 0, 0, 0.2)',
      fontSize: '24px',
      verticalAlign: 'middle',
      cursor: 'pointer',
      transition: 'color 0.3s',
      '&:hover': {
        color: token.colorPrimaryActive,
      },
    },
    lang: {
      width: 42,
      height: 42,
      lineHeight: '42px',
      position: 'fixed',
      right: 16,
      borderRadius: token.borderRadius,
      ':hover': {
        backgroundColor: token.colorBgTextHover,
      },
    },
    container: {
      display: 'flex',
      flexDirection: 'column',
      height: '100vh',
      overflow: 'auto',
      backgroundColor: '#ffffff',
    },
    loginContainer: {
      display: 'flex',
      flex: '1',
      '@media screen and (max-width: 768px)': {
        flexDirection: 'column',
      },
    },
    loginForm: {
      flex: 1,
      padding: '32px',
      '@media screen and (max-width: 768px)': {
        padding: '16px',
      },
    },
    adSection: {
      flex: 1,
      padding: '32px',
      backgroundImage: `linear-gradient(145deg, rgba(173, 216, 230, 0.5), rgba(221, 160, 221, 0.5))`,
      backgroundSize: '100% 100%',
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      textAlign: 'center',
      color: '#333',
      '@media screen and (max-width: 768px)': {
        display: 'none',
      },
    },
    adTitle: {
      fontSize: '2rem',
      fontWeight: 'bold',
      marginBottom: '16px',
    },
    adDescription: {
      fontSize: '1.2rem',
      marginBottom: '24px',
    },
    adButton: {
      padding: '12px 24px',
      fontSize: '1rem',
      color: '#fff',
      backgroundColor: token.colorPrimary,
      border: 'none',
      borderRadius: token.borderRadius,
      cursor: 'pointer',
      transition: 'background-color 0.3s',
      '&:hover': {
        backgroundColor: token.colorPrimaryHover,
      },
    },
    adCanvas :{
      position: 'absolute',
      top: 0,
      left: 0,
      width: '100%',
      height: '100%',
      pointerEvents: 'none'
    },
    adImg: {
      width: '300px', /* 根据需要调整宽度 */
      height: 'auto' /* 保持宽高比 */
    }
  };
});

export default useStyles;
