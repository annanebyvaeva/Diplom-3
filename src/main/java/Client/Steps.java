package Client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;

import static Client.BaseApi.spec;
import static Client.Endpoints.*;
import static io.restassured.RestAssured.given;

public class Steps {

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user){
        return given()
                .header("Content-type", "application/json")
                .spec(spec)
                .and()
                .body(user)
                .when()
                .post(USER_LOGIN_URI)
                .then();
    }
    @Step("Регистрация пользователя")
    public ValidatableResponse registrationUser(User user){
        return given()
                .header("Content-type", "application/json")
                .spec(spec)
                .and()
                .body(user)
                .when()
                .post(USER_REGISTRATION_URI)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .spec(spec)
                .header("Content-type", "application/json")
                .delete(USER_DATA_URI)
                .then();
    }
    @Step("Выход из аккаунта пользователя")
    public ValidatableResponse logoutUser(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .spec(spec)
                .header("Content-type", "application/json")
                .post(USER_LOGOUT_URI)
                .then();
    }
}
