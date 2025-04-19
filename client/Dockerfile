FROM node:alpine

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN npm install -g @angular/cli && npm install && npm run build

EXPOSE 4200

CMD ["npm", "run", "start"]
