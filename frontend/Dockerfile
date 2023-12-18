# Use a Node 16 base image
FROM node:21-alpine as BUILD_IMAGE
# Set the working directory to /app inside the container
WORKDIR /app
# Copy app files

COPY package.json .
RUN npm install
COPY . .
RUN npm run build

#FROM node:21-alpine as PRODUCTION_IMAGE
#WORKDIR /app
#COPY --from=BUILD_IMAGE /app/dist/ /app/dist/
#EXPOSE 3000
#COPY package.json .
#COPY vite.config.js .
CMD ["npm", "run", "preview"]
