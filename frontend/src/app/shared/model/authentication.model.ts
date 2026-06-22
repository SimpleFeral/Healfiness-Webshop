import { UserSummary } from "./user.model"

export interface LoginRequestModel {
    login?: string
    password?: string
}

export interface AuthResponse {
    accessToken?: string
    refreshToken?: string
    expiresIn?: number
    userSummery?: UserSummary
}
