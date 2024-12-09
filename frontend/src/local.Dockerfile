FROM node:21.1.0 as dev

RUN mkdir /app
WORKDIR /app

# ENV NODE_ENV production

COPY package*.json ./
COPY yarn.lock ./
COPY .env.local ./

RUN yarn

EXPOSE 3000

CMD ["yarn" , "dev"]