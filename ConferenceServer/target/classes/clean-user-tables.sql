BEGIN TRANSACTION;
  DROP TABLE IF EXISTS AISESUserSocialLogin;
  DROP TABLE IF EXISTS SocialLogin;
  DROP TABLE IF EXISTS SocialLoginType;
  DROP TABLE IF EXISTS AISESUser;
  COMMIT;
END TRANSACTION
