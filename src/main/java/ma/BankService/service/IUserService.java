package ma.BankService.service;


import ma.BankService.dtos.user.PermissionVo;
import ma.BankService.dtos.user.RoleVo;
import ma.BankService.dtos.user.UserVo;

public interface IUserService {
    void save(UserVo user);

    void save(RoleVo role);

    void save(PermissionVo vo);

    RoleVo getRoleByName(String role);

    PermissionVo getPermissionByName(String authority);

}
