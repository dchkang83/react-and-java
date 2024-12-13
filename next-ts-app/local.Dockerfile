FROM node:21.1.0 as dev

RUN mkdir /app
WORKDIR /app

# ENV NODE_ENV production

COPY src/package*.json ./
COPY src/yarn.lock ./
COPY src/.env.local ./

RUN yarn

EXPOSE 3000

CMD ["yarn" , "dev"]