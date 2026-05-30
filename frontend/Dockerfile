# syntax=docker/dockerfile:1

ARG NODE_VERSION=25.9.0

FROM node:${NODE_VERSION}-alpine

WORKDIR /usr/src/app

RUN --mount=type=bind,source=frontend/package.json,target=package.json \
    --mount=type=bind,source=frontend/package-lock.json,target=package-lock.json \
    --mount=type=cache,target=/root/.npm \
    npm ci

USER node

COPY ./frontend .

EXPOSE 4200

CMD npm start
