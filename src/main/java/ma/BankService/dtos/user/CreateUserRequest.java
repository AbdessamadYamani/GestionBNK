package ma.BankService.dtos.user;

public record CreateUserRequest(String username, String password, String email) {
}
